package lowleveldesign.splitexpensesharingsystem;

interface SplitStrategy {
    void shareExpense();
}

public class Main {
}

class User {
    //name, id, splitamount, balanceamount,date, organization/community
}

class Group {
    //name, id, List<user> groupUser, totalExpense,balance , date, org/community

}

class ManageUsers {
    //List<user> AllUsers
    public ManageUsers() {
        //  AllUsers =  new ArrayList<User>();
    }
    //addUser(User u)
    //RemoveUser(User u)
    //updateUser(User u)

}

class SplitByExactAmount implements SplitStrategy {
    public SplitByExactAmount(Group u) {

    }

    @Override
    public void shareExpense() {

    }

    //algo for setting hte percentage for each users
    void setSplitAmounttoEachUser(Group g) {

    }

}


class PercentageSplit implements SplitStrategy {
    public PercentageSplit(Group u) {

    }

    @Override
    public void shareExpense() {

    }

    //algo for setting hte percentage for each users
    void setPercentage(Group g) {

    }

}


class EqualSplit implements SplitStrategy {
    public EqualSplit(Group u) {

    }

    @Override
    public void shareExpense() {

    }
    //algo for getting the ttl no of hte user list and expense  to calculate average amount
}

class ManageExpenses {

    public ManageExpenses(Group g) {

    }

    // add total expense

}

class ManageGroups {
    //List<Groups> AllGroups
    public ManageGroups() {
        // AllGroups = new ArrayList<Group>();
    }
    //CreateGroup(List<User> users)
    //UpdateGroup(Group g)
    //removeGroup(Group g)
    //addUsertotheGroup(Group g, User u)
    //removeUserfromtheGroup(Group g, User u)
    //updateUserFromtheGroup(Group g , user u)

}



/*
* LLD Question 4: Design a Splitwise-like Expense Sharing System
Problem Statement:

Design the backend system for an app like Splitwise that allows users to track group expenses and
*  split them fairly.

✅ Functional Requirements:
Users can create groups and add other users.

Any user in a group can add an expense (e.g., "I paid ₹1000 for dinner").

Expenses can be:

Split equally

Split by percentage

Split by exact amounts

The system should maintain a ledger of who owes whom and how much.

Support simplifying debts (e.g., reduce circular debts).

🧩 Key Classes to Think About:
User
Group
Expense
SplitStrategy (Equal, Percent, Exact)
Ledger
📘 Expected Features:
addUser(User u)
createGroup(List<User> users)
addExpense(...)
getBalances(User u)
Use of inheritance/polymorphism for SplitStrategy.
🔧 Bonus:
Group settlements
Notifications/reminders to settle up
*
*
*/
