/**
 *
 * @param {*} func um handler com argumentos
 * @param {number} wait o tempo em ms em que a função esperará para ser recuperada pelo timeout
 * @returns
 */
export function debounce(func, wait){
    let timeout;
    return function() {
        const context = this;
        const args = arguments;
        clearTimeout(timeout);
        timeout = setTimeout(() => {
            func.apply(context, args);
        }, wait);
    };
}

/**
 *
 * @param {*} func a função que será limitada
 * @param {*} limit o tempo de limitação em ms
 * @returns
 */
export function throttle(func, limit){
    let inThrottle = false;
    return function () {
        const args = arguments;
        const context = this;
        if(!inThrottle){
            func.apply(context, args);
            inThrottle = true;
            setTimeout(() => inThrottle = false, limit);
        }
    }
}

export function infiniteScrollHandler(callbackfn, currPageRef, hasMore, isLoading, threshold = 150){

    const container = document.documentElement;
    const scrollHeight = container.scrollHeight;
    const scrollTop = container.scrollTop;
    const clientHeight = container.clientHeight;

    if(!isLoading.value && hasMore.value && (scrollTop + clientHeight >= scrollHeight - threshold)) callbackfn(currPageRef + 1);

}