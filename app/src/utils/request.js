const API_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080';

export async function getJson(url, option) {
  return await fetch(`${API_URL}${url}`, {
    method: 'GET',
    ...option,
  });
}
