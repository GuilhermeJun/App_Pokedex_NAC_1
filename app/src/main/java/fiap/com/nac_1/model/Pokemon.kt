package fiap.com.nac_1.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
        @SerializedName("name") val name: String,
        @SerializedName("sprites") val sprites: Sprites,
)

data class Sprites(
        @SerializedName("front_default") val front: String
)