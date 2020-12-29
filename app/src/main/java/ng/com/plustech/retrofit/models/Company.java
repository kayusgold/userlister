package ng.com.plustech.retrofit.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Company {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("catchPhrase")
    @Expose
    private String catchPhrase;
    @SerializedName("bs")
    @Expose
    private String bs;

    public final static Parcelable.Creator<Company> CREATOR = new Parcelable.Creator<Company>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        public Company[] newArray(int size) {
            return (new Company[size]);
        }

    }
            ;

    protected Company(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.catchPhrase = ((String) in.readValue((String.class.getClassLoader())));
        this.bs = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Company() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("catchPhrase", catchPhrase).append("bs", bs).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(catchPhrase);
        dest.writeValue(bs);
    }

    public int describeContents() {
        return 0;
    }
}