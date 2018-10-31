package com.itliusir.test.hash;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.Set;

@Data
public class SecurityDataitem implements Serializable {
    private static final long serialVersionUID = -5444107457437379462L;
    private Set<String> originalPath;
    private String classname;
    private String methodname;
    private String note;
    private Set<RequestMethod> requestMethod;
    private String type;

    public SecurityDataitem(Set<String> originalPath, String classname, String methodname, String note, Set<RequestMethod> requestMethod, String type) {
        this.originalPath = originalPath;
        this.classname = classname;
        this.methodname = methodname;
        this.note = note;
        this.requestMethod = requestMethod;
        this.type = type;
    }

    public SecurityDataitem() {
    }



    public static class SecurityDataitemBuilder {
        private Set<String> originalPath;
        private String classname;
        private String methodname;
        private String note;
        private Set<RequestMethod> requestMethod;
        private String type;

        public SecurityDataitemBuilder() {
        }

        public SecurityDataitem.SecurityDataitemBuilder setOriginalPath(Set<String> originalPath) {
            this.originalPath = originalPath;
            return this;
        }

        public SecurityDataitem.SecurityDataitemBuilder setClassname(String classname) {
            this.classname = classname;
            return this;
        }

        public SecurityDataitem.SecurityDataitemBuilder setMethodname(String methodname) {
            this.methodname = methodname;
            return this;
        }

        public SecurityDataitem.SecurityDataitemBuilder setNote(String note) {
            this.note = note;
            return this;
        }

        public SecurityDataitem.SecurityDataitemBuilder setRequestMethod(Set<RequestMethod> requestMethod) {
            this.requestMethod = requestMethod;
            return this;
        }

        public SecurityDataitem.SecurityDataitemBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public SecurityDataitem build() {
            return new SecurityDataitem(this.originalPath, this.classname, this.methodname, this.note, this.requestMethod, this.type);
        }
    }
}
