package bj.formation.demoprojet.dtos.responses;

import bj.formation.demoprojet.dtos.AgentDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpResponse<T> {
    private int statusCode;
    private boolean success;
    private String message;
    private T data;
    private Object metadata;
    private Object errors;
    private long timestamp;
    private String path;
}