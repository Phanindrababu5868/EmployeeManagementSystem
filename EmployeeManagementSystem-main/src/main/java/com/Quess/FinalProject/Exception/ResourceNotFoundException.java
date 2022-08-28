package com.Quess.FinalProject.Exception;

public class ResourceNotFoundException extends RuntimeException{

        private String massege;

        public ResourceNotFoundException() {}

        public ResourceNotFoundException(String msg)
        {
            super(msg);
            this.massege = msg;
        }

}
