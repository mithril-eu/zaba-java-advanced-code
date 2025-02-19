package eu.mithril.training.spring.boot.todo.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import eu.mithril.training.spring.boot.todo.model.Todo;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "metadata",
        "todos"
})
public class TodoCollectionResponse implements TodoResponse {

    private static final long serialVersionUID = -6220322296496963477L;

    @JsonProperty("metadata")
    @ApiModelProperty(notes = "Container for any metadata for this request/response")
    private Metadata metadata = new Metadata();

    @JsonProperty("todos")
    @ApiModelProperty(notes = "Multiple todos returned as a payload")
    private List<Todo> todos;

    public TodoCollectionResponse() {
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(final Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(final List<Todo> todos) {
        this.todos = todos;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TodoCollectionResponse that = (TodoCollectionResponse) o;

        if (metadata != null ? !metadata.equals(that.metadata) : that.metadata != null) return false;
        return todos != null ? todos.equals(that.todos) : that.todos == null;
    }

    @Override
    public int hashCode() {
        int result = metadata != null ? metadata.hashCode() : 0;
        result = 31 * result + (todos != null ? todos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TodoServiceResponse{");
        sb.append("metadata=").append(metadata);
        sb.append(", todos=").append(todos);
        sb.append('}');
        return sb.toString();
    }
}
