package com.monotonic.testing.m2;

import org.junit.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

public class CafeTest {

    public static final int ESPRESSO_BEANS=CoffeeType.Espresso.getRequiredBeans();
    public static final int NO_MILK = 0;
    public static final int NO_BEANS = 0;

    private Cafe cafe;
/*
    @BeforeClass
    public static void beforeClass(){
        System.out.println("before class");
    }

    @AfterClass
    public static void afterClass(){
        System.out.println("after class");
    }
*/
    @Before
    public void before(){
        cafe=new Cafe();
    }
/*
    @After
    public void after(){
        System.out.println("after");
    }
*/
    @Test
    public void canBrewEspresso(){
        //given clause
        withBeans();

        // when clause
        Coffee coffee=cafe.brew(CoffeeType.Espresso);

        // it is an espresso!
        // no milk
        // that we've got enough coffee

        // then clause
        assertThat(coffee,hasProperty("beans",equalTo(ESPRESSO_BEANS))); // with Hamcrest
        Assert.assertEquals("Wrong coffee type",CoffeeType.Espresso,coffee.getType());
        Assert.assertEquals("Wrong amount of milk", NO_MILK,coffee.getMilk());
   //     Assert.assertEquals("Wrong number of beans",ESPRESSO_BEANS,coffee.getBeans());

    }

    @Test
    public void brewingEspressoConsumesBean(){
        //given clause
        withBeans();

        // when clause
        Coffee coffee=cafe.brew(CoffeeType.Espresso);

        // then clause
        Assert.assertEquals(NO_BEANS,cafe.getBeansInStock());
    }

    // then clause
    @Test(expected = IllegalStateException.class)
    public void latteRequiresMilk(){

        //give
        withBeans();

        //when
        Coffee coffee=cafe.brew(CoffeeType.Latte);

    }

    @Test
    public void canBrewLatte(){
        //given
        withBeans();
        cafe.restockMilk(CoffeeType.Latte.getRequiredMilk());

        //when
        Coffee coffee=cafe.brew(CoffeeType.Latte);

        //then
        Assert.assertEquals("",CoffeeType.Latte,coffee.getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustRestockMilk(){
        //given
     //   Cafe cafe=new Cafe(); implemented in @Before

        //when
        cafe.restockMilk(NO_MILK);
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustRestockBeans(){
        //given
        Cafe cafe=new Cafe();

        //when
        cafe.restockBeans(NO_BEANS);
    }
    private void withBeans() {

        cafe.restockBeans(ESPRESSO_BEANS);

    }
}
