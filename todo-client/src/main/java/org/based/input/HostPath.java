package org.based.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
@Getter
public class HostPath {
    @NotNull
    private final String projectPath;
    @NotNull
    private final String userPath;
    @NotNull
    private final String taskPath;
}
