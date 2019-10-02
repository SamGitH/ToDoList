package ru.pushapptest.todolist.models;

import ru.pushapptest.todolist.data.database.models.TodoDB;

public class Todo {

    public long id;
    public String headText;
    public String mainText;
    public String status;

    public Todo(long id, String headText, String mainText, String status) {
        this.id = id;
        this.headText = headText;
        this.mainText = mainText;
        this.status = status;
    }

//    public Todo (Parcel in){
//        headText = in.readString();
//        mainText = in.readString();
//        status = in.readString();
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(headText);
//        dest.writeString(mainText);
//        dest.writeString(status);
//    }
//    public static final Parcelable.Creator<Todo> CREATOR = new Parcelable.Creator<Todo>(){
//
//        @Override
//        public Todo createFromParcel(Parcel source) {
//            return new Todo(source);
//        }
//
//        @Override
//        public Todo[] newArray(int size) {
//            return new Todo[size];
//        }
//    };
}
