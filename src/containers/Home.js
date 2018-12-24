import React, { PureComponent } from 'react';
import { stringify } from 'query-string';

import { MapCarousel, Search } from '../components';

class Home extends PureComponent {
  state = {
    startFloor: null,
    endFloor: null,
    startPlace: null,
    endPlace: null,
    // Way: 'elevator',
  };

  componentDidMount () {
    const params = new URLSearchParams(this.props.location.search);
    const startFloor = params.get('start_floor');
    const startPlace = params.get('start_place');
    this.setState((state) => ({
      ...state,
      startFloor,
      startPlace,
    }));
  }

  componentDidUpdate (prevProps, prevState) {
    const { startPlace, endPlace } = this.state;

    if (startPlace !== null && endPlace !== null) {
      this.fetchShortcut();
    }
  }

  fetchShortcut = async () => {
    let json;

    try {
      const response = await fetch(`/shortcut?${stringify(this.state)}`, {
        method: 'GET',
      });
      json = await response.json();
    } catch (err) {
      console.error(err);
      // Throw new Error(err);
    }

    console.log(json);
  };

  onSelectNode = (e, name) => {
    const floor = `${name}Floor`;
    const place = `${name}Place`;

    this.setState((state) => ({
      ...state,
      [floor]: e.floor,
      [place]: e.value,
    }));
  };

  onSelectWay = (e) => {
    const value = e.currentTarget.value;
    this.setState((state) => ({
      ...state,
      way: value,
    }));
  };

  render () {
    return (
      <>
        <Search onChange={this.onSelectNode} onClick={this.onSelectWay} {...this.state} />
        <MapCarousel />
      </>
    );
  }
}

export default Home;
