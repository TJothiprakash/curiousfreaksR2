package lowleveldesign.onlinecodeeditor;

import java.util.Map;

class ExecutionRequest {
    String code;
    Language language;
    Map<String, String> inputParams; // stdin, args, etc.
}

