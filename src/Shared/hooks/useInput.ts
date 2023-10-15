import {useState} from "react";

export const useInput = (initial: boolean) => {
    const [state, setState] = useState<boolean>(initial)
    const changeHandler = () => {
        setState(prevState => !prevState)
    }

    return {state, changeHandler}
}