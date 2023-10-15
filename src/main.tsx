import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App/App'
import './index.css'
import {YMaps} from "@pbe/react-yandex-maps";
import {Provider} from "react-redux";
import {store} from "./App/store";

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <Provider store={store}>
      <YMaps query={{ lang: 'en_RU' }}>
          <App/>
      </YMaps>
  </Provider>,
)
