import com.choremart.Address
import com.choremart.State
import com.choremart.security.ChoreUser
import com.choremart.security.ChoreUserRole
import com.choremart.security.Role

class BootStrap {
    def init = { servletContext ->
        createRole()
        createStateList()
        createUser()
    }
    def destroy = {
    }
    void createRole() {
        Role superAdmin = Role.findByAuthority("ROLE_SUPER_ADMIN")
        if (!superAdmin) {
            superAdmin = new Role(authority: 'ROLE_SUPER_ADMIN').save(flush: true)
        }
    }
    void createUser() {
        ChoreUser adminUser = ChoreUser.findByUsername('admin')
        if (!adminUser) {
            //create address for this user
            Address adminAdd = new Address(address1: "Lane 2, Block 35", city: "New York", state: State.findByAbbreviation('AL'),zip: '23456').save(flush: true)
            adminUser = new ChoreUser(username: 'admin', password: 'admin', enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false,address: adminAdd).save(flush: true)
            //Map role to user
            new ChoreUserRole(choreUser: adminUser, role: Role.findByAuthority("ROLE_SUPER_ADMIN")).save(flush: true)
        }
    }

    void createStateList() {
        State state
        for (def stateDef : statesProvinces) {
            state = State.findByAbbreviation(stateDef.abbreviation)
            if (!state) {
               new State(name: stateDef.name, abbreviation: stateDef.abbreviation).save(flush: true)
            }
        }
    }
    def statesProvinces =[
            [name: "Alabama",abbreviation: "AL"],
            [name: "Alaska",abbreviation: "AK"],
            [name: "Arizona",abbreviation: "AZ"],
            [name: "Arkansas",abbreviation: "AR"],
            [name: "California",abbreviation: "CA"],
            [name: "Colorado",abbreviation: "NC"],
            [name: "Connecticut",abbreviation: "CT"],
            [name: "Delaware",abbreviation: "DE"],
            [name: "District of Columbia",abbreviation: "DC"],
            [name: "Florida",abbreviation: "FL"],
            [name: "Georgia",abbreviation: "GA"],
            [name: "Hawaii",abbreviation: "HI"],
            [name: "Idaho",abbreviation: "ID"],
            [name: "Illinois",abbreviation: "IL"],
            [name: "Indiana",abbreviation: "IN"],
            [name: "Iowa",abbreviation: "IA"],
            [name: "Kansas",abbreviation: "KS"],
            [name: "Kentucky",abbreviation: "KY"],
            [name: "Louisiana",abbreviation: "LA"],
            [name: "Maine",abbreviation: "ME"],
            [name: "Maryland",abbreviation: "MD"],
            [name: "Massachusetts",abbreviation: "MA"],
            [name: "Michigan",abbreviation: "MI"],
            [name: "Minnesota",abbreviation: "Mn"],
            [name: "Mississippi",abbreviation: "MS"],
            [name: "Missouri",abbreviation: "MO"],
            [name: "Montana",abbreviation: "MT"],
            [name: "Nebraska",abbreviation: "NE"],
            [name: "Nevada",abbreviation: "NV"],
            [name: "New Hampshire",abbreviation: "NH"],
            [name: "New Jersey",abbreviation: "NJ"],
            [name: "New Mexico",abbreviation: "NM"],
            [name: "New York",abbreviation: "NY"],
            [name: "North Carolina",abbreviation: "NC"],
            [name: "North Dakota",abbreviation: "ND"],
            [name: "Ohio",abbreviation: "OH"],
            [name: "Oklahoma",abbreviation: "OK"],
            [name: "Oregon",abbreviation: "OR"],
            [name: "Pennsylvania",abbreviation: "PA"],
            [name: "Rhode Island",abbreviation: "RI"],
            [name: "South Carolina",abbreviation: "SC"],
            [name: "South Dakota",abbreviation: "SD"],
            [name: "Tennessee",abbreviation: "TN"],
            [name: "Texas",abbreviation: "TX"],
            [name: "Utah",abbreviation: "UT"],
            [name: "Vermont",abbreviation: "VT"],
            [name: "Virginia",abbreviation: "VA"],
            [name: "Washington",abbreviation: "WA"],
            [name: "West Virginia",abbreviation: "WV"],
            [name: "Wisconsin",abbreviation: "WI"],
            [name: "Wyoming",abbreviation: "WY"]
    ]
}
