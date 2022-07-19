package cesi.sourcesapi.dto;

import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties("status")
public class Response {

    private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
    private HttpStatus status = HttpStatus.OK;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    public Response(Boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public Response(Boolean success, String error, HttpStatus status) {
        this.success = success;
        this.error = error;
        this.status = status;
    }

    public Response(Boolean success, HttpStatus status, Map<String, String> errors) {
        this.success = success;
        this.status = status;
        this.errors = errors;
    }

    public Response() {
    }

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}


}
