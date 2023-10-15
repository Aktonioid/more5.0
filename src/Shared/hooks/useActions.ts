import actionCreators from '../../App/store/actions/index'
import {bindActionCreators} from "redux";
import {useAppDispatch} from "./useAppDispatch";


export const useActions = () => {
    const dispatch = useAppDispatch()
    return bindActionCreators(actionCreators, dispatch)
}