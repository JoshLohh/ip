public class TaskList {
    private String[] tasks = new String[100];
    private int count = 0;

    //add tasks

    public void addTask(String task){
        tasks[count++] = task;
        System.out.println("  -----------------------------------------------------------------");
        System.out.println("  added: " + task);
        System.out.println("  -----------------------------------------------------------------");
    }

    public void listTasks() {
        System.out.println("  -----------------------------------------------------------------");
        for (int i = 0; i < count; i++){
            System.out.println("  " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("  -----------------------------------------------------------------");
    }
}
