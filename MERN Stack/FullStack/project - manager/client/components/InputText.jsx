import TextField from "@mui/material/TextField";
import React, { useState } from "react";

const InputText = (props) => {
  const { id, isError, isDirty = false, ...rest } = props;

  const [isTouched, setIsTouched] = useState(isDirty);

  return (
    <TextField
      // Material UI input style
      variant="outlined"

      // Make input take full card width
      fullWidth

      color="primary"

      onInput={(e) => {
        if (rest.onChange) {
          rest.onChange(e);
        }

        // User started typing
        setIsTouched(true);
      }}

      // Show red border when validation fails
      error={isTouched && isError}

      id={id}

      {...rest}
    />
  );
};

export default InputText;