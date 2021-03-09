package com.example.simpledagger.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.simpledagger.model.UserModel;
import com.example.simpledagger.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<UserModel> modelMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MutableLiveData<UserModel> getModelMutableLiveData() {
        loadData();
        return modelMutableLiveData;
    }

    private void loadData() {
        isLoading.setValue(true);
        disposable.add(userRepository.modelSingle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserModel>() {
                    @Override
                    public void onSuccess(UserModel userModel) {
                        isLoading.setValue(false);
                        modelMutableLiveData.setValue(userModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue(e.getMessage());
                        isLoading.setValue(false);
                    }
                }));
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

    }
}
