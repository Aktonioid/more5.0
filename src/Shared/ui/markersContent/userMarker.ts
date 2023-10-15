export const userMarker = (): HTMLDivElement => {
    const circle = document.createElement<'div'>('div')
    circle.classList.add('user_marker')
    return circle
}