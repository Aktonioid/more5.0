async function fetchData <T>(url: string, config: any): Promise<T> {
    const response = await fetch(url, config)
    return await response.json()
}

export {
    fetchData
}