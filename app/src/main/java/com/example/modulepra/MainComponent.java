package com.example.modulepra;

import com.example.modulepra.di.ApplicationComponent;

import dagger.Component;
/**很多東西是applicationcomponent提供的*/
/*scope要跟compenent scope相同*/
@MainActScoped
@Component(
        dependencies = ApplicationComponent.class,
        modules = {
                MainModule.class
        }
)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
