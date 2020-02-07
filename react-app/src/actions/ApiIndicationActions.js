import {
  API_CHAIN_FINISHED_ERRONEOUSLY,
  API_CHAIN_FINISHED_SUCCESSFULLY,
  API_CHAIN_STARTED,
} from './types/ApiIndicationActionTypes';


export const apiChainStarted = () => ({
  type: API_CHAIN_STARTED,
});

export const apiChainFinishedSuccess = () => ({
  type: API_CHAIN_FINISHED_SUCCESSFULLY,
});

export const apiChainError = () => ({
  type: API_CHAIN_FINISHED_ERRONEOUSLY,
});
