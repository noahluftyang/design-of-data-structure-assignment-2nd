namespace Elevatorsim
{
    class Floor
    {
        public int userWaiting;
        public int userGoing;
        public int AllUser { get { return userGoing + userWaiting; } }

        public string name;

        public void DoTick()
        {
            userGoing /= 2;
            if (userWaiting > 60)
                userWaiting = (int)(userWaiting * 0.94);
        }
        public int LoadUserTo(Elevator elevator)
        {
            int loadedUsers = elevator.LoadUser(userWaiting);
            userWaiting -= loadedUsers;
            return loadedUsers;
        }
    }
}
