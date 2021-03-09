// Generated by Dagger (https://dagger.dev).
package com.example.simpledagger;

import androidx.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public MainActivity_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new MainActivity_MembersInjector(viewModelFactoryProvider);}

  @Override
  public void injectMembers(MainActivity instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  public static void injectViewModelFactory(MainActivity instance,
      ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
