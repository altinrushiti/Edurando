export function isPasswordRepeatValid(password, passwordRepeat) {
    return passwordRepeat.length > 0 && password === passwordRepeat && isPasswordValid(password, passwordRepeat)
}

export function showPasswordError(password, passwordRepeat) {
    const list = [];
    if (password.length < 8) {
        list.push("Password is at least 8 characters")
    }
    if (!/[a-z]/.test(password)) {
        list.push("Password contain at least one lowercase letter")
    }
    if (!/[A-Z]/.test(password)) {
        list.push("Password contain at least one uppercase letter")
    }
    if (!/\d/.test(password)) {
        list.push("Password contain at least one number")
    }
    if (!/[^a-zA-Z0-9\s]/.test(password)) {
        list.push("Password contain at least one special character")
    }
    if (passwordRepeat.length > 0 && password !== passwordRepeat) {
        list.push("Passwords match")
    }
    return list
}

export function isPasswordValid(password, passwordRepeat) {
    return showPasswordError(password, passwordRepeat).length === 0

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