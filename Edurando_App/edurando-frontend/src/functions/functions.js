export function showPasswordRepeatError(password, passwordRepeat) {
    return passwordRepeat.length > 0 && password !== passwordRepeat
}

export function showPasswordError(password) {
    return password.length > 0 &&
        (password.length < 8 ||
        !/[a-z]/.test(password) ||
        !/[A-Z]/.test(password) ||
        !/\d/.test(password) ||
        !/[^a-zA-Z0-9\s]/.test(password))
}

export function transformData(data) {
    const transformedData = {};

    for (const item of data) {
        const subjectName = item.subject.name;

        if (!transformedData[subjectName]) {
            transformedData[subjectName] = [];
        }

        transformedData[subjectName].push({
            id: item.id,
            name: item.name
        });
    }

    return transformedData;
}