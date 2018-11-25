package tn.esprit.miafamilia10.Models;

public class Task {
    private int id ;
    private String task ;

    public Task(int id, String task) {
        this.id = id;
        this.task = task;
    }

    public Task(int id) {
        this.id = id;
    }

    public Task(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setTask(String task) {
        this.task = task;
    }
}

