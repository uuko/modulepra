package com.example.modulepra.db;

import java.util.List;

public interface LoadDBListener {
    interface  InsertListener{
        void  onComplete(String a);
        void  onError(String a);
    }

    interface  SearchListener{
        void  onSharedFinished(List<MainTable> sharedEntityList );
        void  onError(String a);
    }

    interface  DeletListener{
        void  onDeleteComplete();
        void  onError(String a);
    }
}
