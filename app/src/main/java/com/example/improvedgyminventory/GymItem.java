package com.example.improvedgyminventory;

import android.os.Parcel;
import android.os.Parcelable;

public class GymItem implements Parcelable {

    String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int count;

    public GymItem(String name, int co) {
        itemName = name;
        count = co;
    }

    protected GymItem(Parcel in) {
        itemName = in.readString();
        count = in.readInt();
    }

    public static final Creator<GymItem> CREATOR = new Creator<GymItem>() {
        @Override
        public GymItem createFromParcel(Parcel in) {
            return new GymItem(in);
        }

        @Override
        public GymItem[] newArray(int size) {
            return new GymItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeInt(count);
    }
}
