package com.ibay.backend.theory.b_theory.question11;

public class Nr5isD {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does D stand for in SOLID? Explain the principle.
    //todo B Give an example. Write actual or pseudo code.
    // Answer: D is for Dependency Inversion principle
    // This principle states that no entities should be dependent on a defined module but rather an abstraction that could be swapped out easily if needed.

    //todo Example of the wrong way:

    class PayPalConnection {
        Boolean verifyPayment(Payment payment) {return true;};
    }

    class Payment {}

    class PaymentProcessing {

        private PayPalConnection payPalConnection;

        public PaymentProcessing(PayPalConnection connection) {
            payPalConnection = connection;
        }

        public Boolean verifyPayment(Payment payment) {
            return this.payPalConnection.verifyPayment(payment);
        }

    }

    //todo Better way of solving this:

    interface PaymentServerConnection {

        Boolean verifyPayment(Payment payment);

    }

    class PayPalServerConnection implements PaymentServerConnection {

        @Override
        public Boolean verifyPayment(Payment payment) {
            return true;
        }
    }

    class PaymentProcessing1 {

        private PaymentServerConnection connection;

        public PaymentProcessing1 (PaymentServerConnection connection) {
            this.connection = connection;
        }

        public Boolean verifyPayment(Payment payment) {
            return this.connection.verifyPayment(payment);
        }

    }
}
