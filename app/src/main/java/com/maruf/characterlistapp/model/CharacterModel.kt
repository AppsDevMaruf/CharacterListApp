package com.maruf.characterlistapp.model


import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class CharacterModelItem(
    @SerializedName("actor")
    val actor: String?,
    @SerializedName("alive")
    val alive: Boolean?,
    @SerializedName("alternate_actors")
    val alternateActors: List<String?>?,
    @SerializedName("alternate_names")
    val alternateNames: List<String?>?,
    @SerializedName("ancestry")
    val ancestry: String?,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("eyeColour")
    val eyeColour: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("hairColour")
    val hairColour: String?,
    @SerializedName("hogwartsStaff")
    val hogwartsStaff: Boolean?,
    @SerializedName("hogwartsStudent")
    val hogwartsStudent: Boolean?,
    @SerializedName("house")
    val house: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("patronus")
    val patronus: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("wand")
    val wand: Wand?,
    @SerializedName("wizard")
    val wizard: Boolean?,
    @SerializedName("yearOfBirth")
    val yearOfBirth: Int?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Wand::class.java.classLoader),
        parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    @Keep

    data class Wand(
        @SerializedName("core")
        val core: String?,
        @SerializedName("length")
        val length: Double?,
        @SerializedName("wood")
        val wood: String?
    ):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(core)
            parcel.writeValue(length)
            parcel.writeString(wood)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Wand> {
            override fun createFromParcel(parcel: Parcel): Wand {
                return Wand(parcel)
            }

            override fun newArray(size: Int): Array<Wand?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(actor)
        parcel.writeValue(alive)
        parcel.writeStringList(alternateActors)
        parcel.writeStringList(alternateNames)
        parcel.writeString(ancestry)
        parcel.writeString(dateOfBirth)
        parcel.writeString(eyeColour)
        parcel.writeString(gender)
        parcel.writeString(hairColour)
        parcel.writeValue(hogwartsStaff)
        parcel.writeValue(hogwartsStudent)
        parcel.writeString(house)
        parcel.writeString(id)
        parcel.writeString(image)
        parcel.writeString(name)
        parcel.writeString(patronus)
        parcel.writeString(species)
        parcel.writeParcelable(wand, flags)
        parcel.writeValue(wizard)
        parcel.writeValue(yearOfBirth)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterModelItem> {
        override fun createFromParcel(parcel: Parcel): CharacterModelItem {
            return CharacterModelItem(parcel)
        }

        override fun newArray(size: Int): Array<CharacterModelItem?> {
            return arrayOfNulls(size)
        }
    }
}
