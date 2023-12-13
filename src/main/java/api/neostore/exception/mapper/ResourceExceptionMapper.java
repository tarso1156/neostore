package api.neostore.exception.mapper;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;

import api.neostore.exception.ResourceException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
class NotFound {
	String message;
}

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ResourceExceptionMapper implements ExceptionMapper<ResourceException> {
	@Override
	public Response toResponse(ResourceException ex) {
		NotFound[] notFoundList = new NotFound[]{ new NotFound(ex.getMessage()) };
		return Response.status(ex.getHttpStatus()).entity((new Gson())
			.toJson(notFoundList)).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}