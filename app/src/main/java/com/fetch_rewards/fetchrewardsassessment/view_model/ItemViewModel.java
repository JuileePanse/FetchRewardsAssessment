package com.fetch_rewards.fetchrewardsassessment.view_model;

import android.util.Log;
import com.fetch_rewards.fetchrewardsassessment.WebAPIService;
import com.fetch_rewards.fetchrewardsassessment.models.Item;
import com.fetch_rewards.fetchrewardsassessment.utils.RetrofitService;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemViewModel extends ViewModel {
    private MutableLiveData<List<Item>> itemList;

    public LiveData<List<Item>> getItems() {
        //if the list is null
        if (itemList == null) {
            itemList = new MutableLiveData<>();
            getItems1();
        }

        //finally we will return the list
        return itemList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void getItems1() {

        WebAPIService service1 = RetrofitService.getInterface();
        Call<List<Item>> jsonCall = service1.getItems();

        jsonCall.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                List<Item> list = response.body();
                System.out.println("List "+list.size());
                removeNulls(list);
                System.out.println("List "+list.size());
                Collections.sort(list, (Comparator) (o1, o2) -> {

                    int sComp = ((Item) o1).getListId() - ((Item) o2).getListId();
                    if (sComp != 0) {
                        return sComp;
                    }

                    return ((Item) o1).getName().compareTo(((Item) o2).getName());
                });
                itemList.setValue(list);
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Log.e("JSON", t.toString());
            }
        });
    }

    static class SortByItemId implements Comparator<Item>
    {
        public int compare(Item a, Item b)
        {
            return a.getListId() - b.getListId();
        }
    }
    static class ItemComparator implements Comparator<Item> {
        public int compare(Item s1, Item s2) {
            return s1.getName().compareTo(s2.getName());
        }
    }

    void removeNulls(List<Item> itemList){
        Iterator<Item> iterator = itemList.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getName() == null || item.getName().equals("")) {
                iterator.remove();
            }
        }
    }

    private static void order(List<Item> persons) {

        Collections.sort(persons, (Comparator) (o1, o2) -> {

            int sComp = ((Item) o1).getListId() - ((Item) o2).getListId();
            if (sComp != 0) {
                return sComp;
            }

            return ((Item) o1).getName().compareTo(((Item) o2).getName());
        });
    }

}
