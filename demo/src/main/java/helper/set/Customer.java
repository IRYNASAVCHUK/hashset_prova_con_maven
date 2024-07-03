package helper.set;

import java.util.Objects;

import core.logger.MyLogger;

public class Customer {
        private int id;
        private String name;

        public Customer(int id, String name) {
                MyLogger.logMethodEntry("initCustomer", this, new Object[] { id, name },
                                new Class<?>[] { int.class, String.class });
                this.id = id;
                this.name = name;
                MyLogger.logMethodExit("initCustomer", this, void.class, null, new Object[] { id, name },
                                new Class<?>[] { int.class, String.class });
        }

        public int getId() {
                MyLogger.logMethodEntry("getId", this, (Object[]) null, new Class<?>[0]);
                MyLogger.logMethodExit("getId", this, int.class, id, (Object[]) null, new Class<?>[0]);
                return id;
        }

        public void setId(int id) {
                if (id < 0)
                        throw new IllegalArgumentException();
                MyLogger.logMethodEntry("setId", this, (new Object[] { id }), new Class<?>[] { int.class });
                this.id = id;
                MyLogger.logMethodExit("setId", this, void.class, null, new Object[] { id },
                                new Class<?>[] { int.class });
        }

        public String getName() {
                MyLogger.logMethodEntry("getName", this, (Object[]) null, new Class<?>[0]);
                MyLogger.logMethodExit("getName", this, String.class, name, (Object[]) null, new Class<?>[0]);
                return name;
        }

        public void setName(String name) {
                MyLogger.logMethodEntry("setName", this, new Object[] { name }, new Class<?>[] { String.class });
                this.name = name;
                MyLogger.logMethodExit("setName", this, void.class, null, new Object[] { name },
                                new Class<?>[] { String.class });
        }

        @Override
        public boolean equals(Object o) {
                MyLogger.logMethodEntry("equals", this, new Object[] { o }, new Class<?>[] { Object.class });
                if (this == o) {
                        MyLogger.logMethodExit("equals", this, boolean.class, true, new Object[] { o },
                                        new Class<?>[] { Object.class });
                        return true;
                }
                if (o == null || getClass() != o.getClass()) {
                        MyLogger.logMethodExit("equals", this, boolean.class, false, new Object[] { o },
                                        new Class<?>[] { Object.class });
                        return false;
                }
                var customer = (Customer) o;
                var returnValue = (id == customer.id && name == customer.name);
                MyLogger.logMethodExit("equals", this, boolean.class, returnValue, new Object[] { o },
                                new Class<?>[] { Object.class });
                return returnValue;
        }

        @Override
        public int hashCode() {
                MyLogger.logMethodEntry("hashCode", this, (Object[]) null, new Class<?>[0]);
                var returnValue = Objects.hash(id, name);
                MyLogger.logMethodExit("hashCode", this, int.class, returnValue, (Object[]) null, new Class<?>[0]);
                return returnValue;
        }

        @Override
        public String toString() {
                MyLogger.logMethodEntry("toString", this, (Object[]) null, new Class<?>[0]);
                var returnValue = String.format("(%d, %s)", id, name);
                MyLogger.logMethodExit("toString", this, String.class, returnValue, (Object[]) null, new Class<?>[0]);
                return returnValue;
        }
}
