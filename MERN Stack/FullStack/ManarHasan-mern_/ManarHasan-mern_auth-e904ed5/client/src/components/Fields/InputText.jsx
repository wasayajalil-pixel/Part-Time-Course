import TextField from "@mui/material/TextField";
import React, { useState } from "react";

const InputText = (props) => {
  const { id, isError, isDirty = false, ...rest } = props;
  const [isTouched, setIsTouched] = useState(isDirty);

  return (
    <TextField
      variant="filled"
      color={"primary"}
      onInput={(e) => {
        if (rest.onChange) {
          rest.onChange(e);
        }
        setIsTouched(true);
      }}
      error={isTouched && isError}
      id={id}
      {...rest}
    />
  );
};

export default InputText;
