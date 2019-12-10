import javax.persistence.*

@Entity
@Table(name = "t_enclosure", schema = "public", catalog = "legal")
open class TEnclosureEntity {
    @get:Id
    @get:Column(name = "id", nullable = false)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "person_id", nullable = true)
    var personId: String? = null
    @get:Basic
    @get:Column(name = "type", nullable = true)
    var type: String? = null
    @get:Basic
    @get:Column(name = "longitude", nullable = true)
    var longitude: String? = null
    @get:Basic
    @get:Column(name = "area_name", nullable = true)
    var areaName: java.sql.Date? = null
    @get:Basic
    @get:Column(name = "latitude", nullable = true)
    var latitude: java.sql.Date? = null


    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "personId = $personId " +
                    "type = $type " +
                    "longitude = $longitude " +
                    "areaName = $areaName " +
                    "latitude = $latitude " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as TEnclosureEntity

        if (id != other.id) return false
        if (personId != other.personId) return false
        if (type != other.type) return false
        if (longitude != other.longitude) return false
        if (areaName != other.areaName) return false
        if (latitude != other.latitude) return false

        return true
    }

}

