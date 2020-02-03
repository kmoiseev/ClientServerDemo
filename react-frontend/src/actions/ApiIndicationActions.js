import {API_CHAIN_FINISHED_ERRONEOUSLY, API_CHAIN_FINISHED_SUCCESSFULLY, START_API_CHAIN} from "./types/ApiIndicationActionTypes";

export const startApiChain = () => ({
    type: START_API_CHAIN
});

export const apiChainFinishedSuccessfully = () => ({
    type: API_CHAIN_FINISHED_SUCCESSFULLY
});

export const apiChainFinishedErroneously = () => ({
    type: API_CHAIN_FINISHED_ERRONEOUSLY
});