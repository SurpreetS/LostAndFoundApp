
/*
            Name        :  Surpreet Singh
            Student ID  :  218663803
            Unit No.    :  SIT305

 */



package sqllitehelper;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {

    int id;
    String fullName, phoneNumber, description, date, location;

    public UserData(String fullName, String phoneNumber, String description, String date, String location) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.date = date;
        this.location = location;
    }

    protected UserData(Parcel in) {
        id = in.readInt();
        fullName = in.readString();
        phoneNumber = in.readString();
        description = in.readString();
        date = in.readString();
        location = in.readString();
    }

    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(fullName);
        dest.writeString(phoneNumber);
        dest.writeString(description);
        dest.writeString(date);
        dest.writeString(location);
    }
}
