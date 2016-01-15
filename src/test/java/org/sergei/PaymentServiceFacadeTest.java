package org.sergei;

/**
 * Created by sergei on 12/22/15.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sergei.business.session.Group;
import org.sergei.business.session.dto.Session;
import org.sergei.facade.PaymentServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class PaymentServiceFacadeTest {

    @Autowired
    private PaymentServiceFacade paymentServiceFacade;

    @Test
    /**
     * Here we create a list of Callable tasks (3 items) and
     * make payments asynchronously.
     */
    public void testCreditPayment() throws Exception {

        int parties = 3;
        Collection<Callable<Integer>> tasks = buildPaymentTasks3(parties);
        Executors.newFixedThreadPool(parties).invokeAll(tasks);

    }

    private Collection<Callable<Integer>> buildPaymentTasks1(int parties) throws InterruptedException,
            BrokenBarrierException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties);

        Collection<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0; i < parties * 2; i++) {
            final int number = i;
            tasks.add(() ->
                    {
                        // all threads are waiting here until all 3 (parties) will arrive
                        cyclicBarrier.await();
                        // then each thread do it's own work
                        doCreditPayment(Arrays.asList(10L * (number + 1), 100L * (number + 1)), number);
                        return number;
                    }
            );
        }
        return tasks;
    }

    private Collection<Callable<Integer>> buildPaymentTasks2(int parties) throws InterruptedException,
            BrokenBarrierException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties);

        Collection<Callable<Integer>> tasks = new ArrayList<>();

        IntStream.range(0, parties * 2)
                .forEach(i -> tasks.add(() ->
                        {
                            // all threads are waiting here until all 3 (parties) will arrive
                            cyclicBarrier.await();
                            // then each thread do it's own work
                            doCreditPayment(Arrays.asList(10L * (i + 1), 100L * (i + 1)), i);
                            return i;
                        }
                ));

        return tasks;
    }

    private Collection<Callable<Integer>> buildPaymentTasks3(int parties) throws InterruptedException,
            BrokenBarrierException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties);

        Collection<Callable<Integer>> tasks =
            Stream.iterate(1, e -> e + 1)
                .map(i -> (Callable<Integer>) () -> {
                    // all threads are waiting here until all 3 (parties) will arrive
                    cyclicBarrier.await();
                    // then each thread do it's own work
                    doCreditPayment(Arrays.asList(10L * (i + 1), 100L * (i + 1)), i);
                    System.out.println("---------------------------------- TEST ------------------------------------");
                    return i;
                }).limit(parties * 2)
                .collect(Collectors.toList());


        return tasks;
    }

    private void doCreditPayment(List<Long> amountList, int userId) {
        Session session = new Session(userId + "", Group.USER);
        // here it use PaymentService to doCredit(amountList)
        // which use BankService to transferMoney(amount) for each amountList in the list
        paymentServiceFacade.creditPayment(session, amountList);
    }
}
