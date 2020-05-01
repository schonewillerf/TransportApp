package adsd.app.ovapp;

public class Profile {

    private int age;
    private int id;
    private String firstName;
    private String lastname;
    private String streetName;
    private String residence;




    public Profile()
    {
        this(0, 0, null, null, null,null);
    }


    public Profile(int id, String firstName,
                   String lastname)
    {
        this(0,0, null, null, null,null);
    }

    public Profile(int id,
                   int age,
                   String firstName,
                   String lastname,
                   String streetName,
                   String residence
                   )

    {
        this.id = id;
        this.age = age;
        this.firstName = firstName;
        this.lastname = firstName;
        this.streetName = streetName;
        this.lastname = lastname;
    }


    public  void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return this.id;
    }


    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public  void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public String getLastname()
    {
        return lastname;
    }


    public  void setAge(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return this.age;
    }

    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }

    public String getStreetName()
    {
        return this.streetName;
    }

    public void setResidence(String residence)
    {
        this.residence = residence;
    }

    public String getResidence()
    {
        return this.residence;
    }
}
