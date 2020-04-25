package com.alessandropacielli.turbofan.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@SpringBootTest
public abstract class RepositoryTest<T> {


    private static final int N = 50;
    private static final String DEVICE = "test";

    private Repository<T> repo;
    private List<T> fakeData;


    @Before
    public void setup() {
        this.doSetup();
        this.fakeData = this.generateFakeData(DEVICE, 2 * N);
        this.insertFakeData(this.fakeData);
        this.repo = this.getRepository();
    }

    @After
    public void teardown() {
        this.deleteFakeData();
        this.doTeardown();
    }

    @Test
    public void getLastMeasurementsShouldReturnFakeData() {
        List<T> result = this.repo.getLastMeasurements(DEVICE, N);
        List<T> mostRecent = fakeData.subList(0, N);
        assertEquals(mostRecent, result);
    }

    public abstract Repository<T> getRepository();

    public abstract List<T> generateFakeData(String device, int n);

    public abstract void insertFakeData(List<T> fakeData);

    public abstract void doSetup();

    public abstract void deleteFakeData();

    public abstract void doTeardown();
}
