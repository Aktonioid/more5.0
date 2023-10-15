export const entityMarker = (): HTMLDivElement => {
    const circle = document.createElement<'div'>('div')
    circle.classList.add('entity_marker')
    return circle
}