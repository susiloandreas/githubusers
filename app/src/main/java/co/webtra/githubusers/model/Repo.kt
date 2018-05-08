package co.webtra.githubusers.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by andreassusilo on 07/05/18.
 */
data class Repo(val name: String,
                val description: String,
                val stargazers_count: Int,
                val watchers_count: Int,
                val private: Boolean,
                val updated_at: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(stargazers_count)
        parcel.writeInt(watchers_count)
        parcel.writeByte(if (private) 1 else 0)
        parcel.writeString(updated_at)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Repo> {
        override fun createFromParcel(parcel: Parcel): Repo {
            return Repo(parcel)
        }

        override fun newArray(size: Int): Array<Repo?> {
            return arrayOfNulls(size)
        }
    }
}