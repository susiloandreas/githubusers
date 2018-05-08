package co.webtra.githubusers.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by andreassusilo on 07/05/18.
 */

data class UserList(val total_count: Int,
                    val items: ArrayList<User>) : Parcelable {

    constructor(source: Parcel) : this(
            source.readInt(),
            source.createTypedArrayList(User.CREATOR)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(total_count)
        writeTypedList(items)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<UserList> = object : Parcelable.Creator<UserList> {
            override fun createFromParcel(source: Parcel): UserList = UserList(source)
            override fun newArray(size: Int): Array<UserList?> = arrayOfNulls(size)
        }
    }
}