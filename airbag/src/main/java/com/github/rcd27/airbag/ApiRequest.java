package com.github.rcd27.airbag;

import android.support.annotation.*;

import java.util.*;

import io.reactivex.Observable;
import io.reactivex.*;
import io.reactivex.disposables.*;
import io.reactivex.subjects.*;
import retrofit2.*;

public class ApiRequest<T> implements Disposable {

  public final BehaviorSubject<RequestState> state = BehaviorSubject
      .createDefault(RequestState.IDLE);
  public final BehaviorSubject<T> response = BehaviorSubject.create();
  public final BehaviorSubject<Throwable> errors = BehaviorSubject.create();
  private final BehaviorSubject<Long> trigger = BehaviorSubject.create();

  private final CompositeDisposable compositeDisposable = new CompositeDisposable();

  public ApiRequest(Single<Response<T>> apiResponse) {
    compositeDisposable.add(
        trigger
            .doOnNext(l -> state.onNext(RequestState.LOADING))
            .switchMap(i -> apiResponse.toObservable())
            .doOnError(this::onError)
            .onErrorResumeNext(Observable.empty())
            .flatMap(retrofitResponse -> {
              if (retrofitResponse.code() == 200) {
                return Observable.just(Objects.requireNonNull(retrofitResponse.body()));
              } else {
                return Observable.error(new HttpException(
                    Response.error(Objects.requireNonNull(
                        retrofitResponse.errorBody()), retrofitResponse.raw())));
              }
            })
            .doOnError(this::onError)
            .onErrorResumeNext(Observable.empty())
            .doOnNext(i -> state.onNext(RequestState.COMPLETE))
            .subscribe(response::onNext)
    );
  }

  // TODO: check how to make user implement what we should do on error
  private void onError(Throwable e) {
    errors.onNext(e);
    state.onNext(RequestState.ERROR);
  }

  @CheckResult
  public Disposable execute(Disposable... d) {
    trigger.onNext(System.currentTimeMillis());
    compositeDisposable.addAll(d);
    return this;
  }

  @Override
  public void dispose() {
    compositeDisposable.clear();
  }

  @Override
  public boolean isDisposed() {
    return compositeDisposable.size() == 0;
  }

  public enum RequestState {
    IDLE, LOADING, COMPLETE, ERROR
  }
}