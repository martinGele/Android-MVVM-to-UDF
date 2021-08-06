package com.martin.samplecompose.data.remote.models

import android.os.Parcel
import android.os.Parcelable

data class PokedexListEntry(
    val pokemonName:String,
    val imageUrl: String,
    val number:Int

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(pokemonName)
        parcel.writeString(imageUrl)
        parcel.writeInt(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokedexListEntry> {
        override fun createFromParcel(parcel: Parcel): PokedexListEntry {
            return PokedexListEntry(parcel)
        }

        override fun newArray(size: Int): Array<PokedexListEntry?> {
            return arrayOfNulls(size)
        }
    }
}
