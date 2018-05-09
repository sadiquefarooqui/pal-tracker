    package io.pivotal.pal.tracker;

    import java.time.LocalDate;

    public class TimeEntry {
        private long id;
        private long projectId;
        private long userId;
        private LocalDate date;
        private int hours;

        public TimeEntry(){

        }
        public TimeEntry(long projectId,long userId,  LocalDate localDate, int hours){

            this.projectId = projectId;
            this.userId = userId;
            this.date = localDate;
            this.hours = hours;
        }

        public TimeEntry(long id, long projectId,long userId,  LocalDate localDate, int hours){
            this.id = id;
            this.projectId = projectId;
            this.userId = userId;
            this.date = localDate;
            this.hours = hours;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getProjectId() {
            return projectId;
        }

        public void setProjectId(long projectId) {
            this.projectId = projectId;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj.getClass().equals(this.getClass())){
                TimeEntry timeEntry = (TimeEntry )obj;
                if(timeEntry.getId() == this.getId()
                        && timeEntry.getDate().equals(this.getDate())
                        && timeEntry.getHours() == this.getHours()
                        && timeEntry.getProjectId() == this.getProjectId()
                        && timeEntry.getUserId() == this.getUserId()){
                    return true;
                }
            }
            return false;

        }
    }
