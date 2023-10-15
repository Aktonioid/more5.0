import {useState} from "react";

export const useSwitcher = (initial: boolean) => {
    const [state, setState] = useState<boolean>(initial)
    const switchHandler = () => {
        setState(prevState => !prevState)
    }
    return {
        state,
        switchHandler
    }
}