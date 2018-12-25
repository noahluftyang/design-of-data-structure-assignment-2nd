import { navigate } from '@reach/router';
import React, { PureComponent } from 'react';
import { stringify } from 'query-string';

import { MapCarousel, Search } from '../components';
import { getJson } from '../utils';

export default class Home extends PureComponent {
  state = {
    startFloor: null,
    endFloor: null,
    startPlace: null,
    endPlace: null,
    startLabel: '',
    endLabel: '',
    // Way: 'elevator',
  };

  componentDidMount() {
    const params = new URLSearchParams(this.props.location.search);
    const startFloor = params.get('start_floor');
    const startPlace = params.get('start_place');
    this.setState(state => ({
      ...state,
      startFloor,
      startPlace,
    }));
  }

  componentDidUpdate(prevProps, prevState) {
    const { startPlace, endPlace } = this.state;

    if (startPlace !== null && endPlace !== null) {
      this.fetchShortcut();
    }
  }

  fetchShortcut = async () => {
    const { startLabel, endLabel } = this.state;
    let json;

    try {
      const response = await getJson(`/path?${stringify(this.state)}`);
      json = await response.json();
    } catch (err) {
      console.error(err);
      // throw new Error(err);
    }

    if (json.message === 'success') {
      console.log(json);
      navigate('/path', {
        state: {
          startLabel,
          endLabel,
          ...json.data,
        },
      });
    }
  };

  onSelectNode = (e, name) => {
    const floor = `${name}Floor`;
    const label = `${name}Label`;
    const place = `${name}Place`;

    this.setState(state => ({
      ...state,
      [floor]: e.floor,
      [label]: e.label,
      [place]: e.value,
    }));
  };

  onSelectWay = e => {
    const value = e.currentTarget.value;
    this.setState(state => ({
      ...state,
      way: value,
    }));
  };

  render() {
    return (
      <>
        <Search onChange={this.onSelectNode} onClick={this.onSelectWay} {...this.state} />
        <MapCarousel />
      </>
    );
  }
}
