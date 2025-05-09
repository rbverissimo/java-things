export function renderizarData(data){
    console.log(data);
    const p = data.split("-");
    if(p.length !== 3) return data;
    const [ano, mes, dia] = p;
    return `${dia}/${mes}/${ano}`;
}