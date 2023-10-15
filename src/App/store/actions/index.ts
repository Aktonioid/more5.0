import * as oneEntityActions from './oneEntityAsyncAction'
import * as manyEntitiesActions from './manyEntitiesAsyncActions'

export default {
    ...oneEntityActions,
    ...manyEntitiesActions
}